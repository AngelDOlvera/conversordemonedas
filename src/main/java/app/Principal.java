package app;

import apis.ExchangeAPI;
import apis.GeckoAPI;
import coins.Coin;
import coins.ConversionData;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Principal principal = new Principal();
        principal.muestraElMenu();
    }

    public void muestraElMenu() {
        System.out.println("***************************************************");
        System.out.println("Bienvenido al conversor de divisas y criptomonedas");
        Scanner teclado = new Scanner(System.in);
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("***************************************************");
            String menu = """
                    Por favor elige la operación que deseas realizar:
                    1 - Conversión de monedas
                    2 - Conversión de criptomonedas
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    nationalCoin();
                    break;
                case 2:
                    cryptoCoin();
                    break;
                case 0:
                    System.out.println("Terminando la sesión...");
                    System.out.println("Gracias por usar nuestra aplicación");
                    System.out.println("******************************************");
                    break;
                default:
                    System.out.println("Opción no válida, por favor intente de nuevo");
            }
        }
    }

    public void nationalCoin() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el número de la moneda nacional que desea convertir:");
        System.out.println("*********************************************************************");
        System.out.println("1: Peso Argentino, 2: Boliviano, 3: Real Brasileño, 4: Peso chileno");
        System.out.println("5: Peso mexicano, 6: Dólar estadounidense, 7: Euro, 8: Peso colombiano");
        System.out.println("*********************************************************************");
        int originCoinOption = scanner.nextInt();
        scanner.nextLine();

        String originCoin = getCoinCode(originCoinOption);

        if (originCoin == null) {
            System.out.println("Opción de moneda no válida.");
            return;
        }

        System.out.println("Ingrese el número de la moneda de destino:");
        int destinyCoinOption = scanner.nextInt();
        scanner.nextLine();

        String destinyCoin = getCoinCode(destinyCoinOption);

        if (destinyCoin == null) {
            System.out.println("Opción de moneda no válida, por favor intente de nuevo");
            return;
        }

        System.out.print("Ingrese la cantidad a convertir: \n");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        Coin coin = new Coin(originCoin, destinyCoin, amount);
        ExchangeAPI exchangeAPI = new ExchangeAPI(coin.getOriginCoin(), coin.getDestinyCoin(), coin.getAmount());
        String jsonResponse = exchangeAPI.getData();

        Gson gson = new Gson();
        ConversionData conversionData = gson.fromJson(jsonResponse, ConversionData.class);

        String formattedDateTime = getCurrentDateTime();
        System.out.println("Esta operacion fue realizada:  " + formattedDateTime);
        System.out.println("1 " + originCoin + " equivale a " + conversionData.getConversionRate() + " " + destinyCoin);
        System.out.println("El valor de la conversión es: " + String.format("%.2f", conversionData.getConversionResult()) + " " + destinyCoin);
    }

    private String getCoinCode(int option) {
        return switch (option) {
            case 1 -> "ARS";
            case 2 -> "BOB";
            case 3 -> "BRL";
            case 4 -> "CLP";
            case 5 -> "MXN";
            case 6 -> "USD";
            case 7 -> "EUR";
            case 8 -> "COP";
            default -> null;
        };
    }

    public void cryptoCoin() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el número de la criptomoneda que desea convertir:");
        System.out.println("1: Bitcoin, 2: Ethereum, 3: Solana, 4: Cardano");
        int originCryptoOption = scanner.nextInt();
        scanner.nextLine();

        String originCrypto = getCryptoCode(originCryptoOption);

        if (originCrypto == null) {
            System.out.println("Opción de criptomoneda no válida.");
            return;
        }

        System.out.println("Ingrese el número de la moneda nacional que desea convertir:");
        System.out.println("1: Peso mexicano, 2: Euro, 3: Dólar estadounidense");
        int destinyCryptoCoinOption = scanner.nextInt();
        scanner.nextLine();

        String destinyCryptoCoin = getDestinyCryptoCoinCode(destinyCryptoCoinOption);

        if (destinyCryptoCoin == null) {
            System.out.println("Opción de moneda no válida.");
            return;
        }

        System.out.print("Ingrese la cantidad de criptomonedas a convertir: \n");
        double cryptoAmount = scanner.nextDouble();
        scanner.nextLine();

        GeckoAPI geckoAPI = new GeckoAPI(originCrypto, destinyCryptoCoin, cryptoAmount);
        String jsonResponse = geckoAPI.getData();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode currencyNode = rootNode.path(originCrypto).path(destinyCryptoCoin);

            if (currencyNode.isMissingNode()) {
                System.out.println("Error: No se pudo obtener la tasa de cambio para " + destinyCryptoCoin);
            } else {
                Double exchangeNationalCoin = currencyNode.asDouble();
                Double finalExchange = exchangeNationalCoin * cryptoAmount;

                String formattedDateTime = getCurrentDateTime();
                System.out.println("Esta operacion fue realizada:  " + formattedDateTime);
                System.out.println("1 " + originCrypto + " equivale a " + exchangeNationalCoin + " " + destinyCryptoCoin);
                System.out.println("El valor de: " + cryptoAmount + " " + originCrypto + "s" + " equivale a: " + String.format("%.2f", finalExchange) + " " + destinyCryptoCoin);
            }
        } catch (IOException e) {
            System.out.println("Error al parsear el JSON: " + e.getMessage());
        }
    }

    private String getCryptoCode(int option) {
        return switch (option) {
            case 1 -> "bitcoin";
            case 2 -> "ethereum";
            case 3 -> "solana";
            case 4 -> "cardano";
            default -> null;
        };
    }

    private String getDestinyCryptoCoinCode(int option) {
        return switch (option) {
            case 1 -> "mxn";
            case 2 -> "eur";
            case 3 -> "usd";
            default -> null;
        };
    }

    private String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return now.format(formatter);
    }
}