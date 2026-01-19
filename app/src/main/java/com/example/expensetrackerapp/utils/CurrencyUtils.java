package com.example.expensetrackerapp.utils;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

/**
 * Utility class for currency formatting.
 */
public class CurrencyUtils {

    /**
     * Format amount with currency symbol.
     */
    public static String formatAmount(double amount, String currency) {
        String symbol = Constants.getCurrencySymbol(currency);
        NumberFormat formatter = NumberFormat.getNumberInstance(Locale.getDefault());
        formatter.setMinimumFractionDigits(2);
        formatter.setMaximumFractionDigits(2);
        return symbol + " " + formatter.format(amount);
    }

    /**
     * Format amount without currency symbol.
     */
    public static String formatAmountWithoutSymbol(double amount) {
        NumberFormat formatter = NumberFormat.getNumberInstance(Locale.getDefault());
        formatter.setMinimumFractionDigits(2);
        formatter.setMaximumFractionDigits(2);
        return formatter.format(amount);
    }

    /**
     * Format amount with sign (+ or -).
     */
    public static String formatAmountWithSign(double amount, String currency, boolean isExpense) {
        String symbol = Constants.getCurrencySymbol(currency);
        NumberFormat formatter = NumberFormat.getNumberInstance(Locale.getDefault());
        formatter.setMinimumFractionDigits(2);
        formatter.setMaximumFractionDigits(2);
        String sign = isExpense ? "-" : "+";
        return sign + symbol + " " + formatter.format(Math.abs(amount));
    }

    /**
     * Parse amount string to double.
     */
    public static double parseAmount(String amountString) {
        if (amountString == null || amountString.isEmpty()) {
            return 0.0;
        }
        try {
            // Remove any non-numeric characters EXCEPT for:
            // - digits
            // - decimal separator (dot or comma)
            // - grouping separator (dot or comma)
            // - minus sign
            // However, since we don't know the locale for sure, relying on
            // NumberFormat.parse is safer if the string comes from formatAmount.

            // First, try simple parsing if it's just a number
            // Remove currency symbol and whitespace
            String cleaned = amountString.replaceAll("[^\\d.,-]", "").trim();

            // If the string contains both . and , attempt to determine which is decimal
            if (cleaned.contains(".") && cleaned.contains(",")) {
                // Usually the last one is the decimal separator
                int lastDot = cleaned.lastIndexOf(".");
                int lastComma = cleaned.lastIndexOf(",");
                if (lastDot > lastComma) {
                    // 1,234.56 -> remove commas
                    cleaned = cleaned.replace(",", "");
                } else {
                    // 1.234,56 -> remove dots, replace comma with dot
                    cleaned = cleaned.replace(".", "").replace(",", ".");
                }
            } else if (cleaned.contains(",")) {
                // Only comma. Could be 1,234 or 12,34 (decimal).
                // Ambiguous without locale. Assumption: user input or standard format.
                // If it has more than 3 chars after comma, likely grouping.
                // But let's assume if it is used for parsing formatted currency from this app,
                // it follows Locale.getDefault().
                NumberFormat formatter = NumberFormat.getNumberInstance(Locale.getDefault());
                Number number = formatter.parse(cleaned);
                if (number != null)
                    return number.doubleValue();
            }

            // Fallback to Double.parseDouble for simple cases or after cleaning
            // Replace comma with dot if it's the only separator remaining and likely a
            // decimal
            if (cleaned.contains(",")) {
                cleaned = cleaned.replace(",", ".");
            }
            return Double.parseDouble(cleaned);
        } catch (Exception e) {
            return 0.0;
        }
    }

    /**
     * Get currency name from code.
     */
    public static String getCurrencyName(String currencyCode) {
        switch (currencyCode) {
            case Constants.CURRENCY_BDT:
                return "Bangladeshi Taka";
            case Constants.CURRENCY_USD:
                return "US Dollar";
            case Constants.CURRENCY_INR:
                return "Indian Rupee";
            case Constants.CURRENCY_EUR:
                return "Euro";
            case Constants.CURRENCY_GBP:
                return "British Pound";
            default:
                return currencyCode;
        }
    }

    /**
     * Get all supported currencies.
     */
    public static String[] getSupportedCurrencies() {
        return new String[] {
                Constants.CURRENCY_BDT,
                Constants.CURRENCY_USD,
                Constants.CURRENCY_INR,
                Constants.CURRENCY_EUR,
                Constants.CURRENCY_GBP
        };
    }

    /**
     * Get currency display names for dropdown.
     */
    public static String[] getCurrencyDisplayNames() {
        return new String[] {
                "৳ BDT - Bangladeshi Taka",
                "$ USD - US Dollar",
                "₹ INR - Indian Rupee",
                "€ EUR - Euro",
                "£ GBP - British Pound"
        };
    }
}
