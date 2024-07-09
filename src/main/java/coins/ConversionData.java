package coins;

import com.google.gson.annotations.SerializedName;

public class ConversionData {
    @SerializedName("conversion_rate")
    private double conversionRate;
    @SerializedName("conversion_result")
    private double conversionResult;

    public ConversionData(double conversionRate, double conversionResult) {
        this.conversionRate = conversionRate;
        this.conversionResult = conversionResult;
    }
    public double getConversionRate() {return conversionRate;
    }
    public double getConversionResult() {
        return conversionResult;
    }

}
