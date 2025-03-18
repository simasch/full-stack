package ch.martinelli.demo.jte.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProductData {

    // Categories of products
    private static final String[] CATEGORIES = {
            "Electronics", "Office Supplies", "Furniture", "Kitchen", "Tools",
            "Clothing", "Books", "Sports", "Health", "Software"
    };

    // Product name parts for generating combinations
    private static final String[] PRODUCT_PREFIXES = {
            "Professional", "Basic", "Premium", "Ultra", "Standard", "Deluxe", "Compact",
            "Portable", "Wireless", "Smart", "Digital", "Advanced", "Modern", "Classic", "Eco"
    };

    private static final String[] PRODUCT_NAMES = {
            "Laptop", "Chair", "Desk", "Monitor", "Keyboard", "Mouse", "Printer", "Tablet",
            "Phone", "Camera", "Scanner", "Headphones", "Speaker", "Microphone", "Router",
            "Server", "Storage", "Battery", "Charger", "Cable", "Adapter", "Projector",
            "Whiteboard", "Notebook", "Pen", "Marker", "Stapler", "Calculator", "Lamp", "Fan"
    };

    private static final String[] PRODUCT_SUFFIXES = {
            "Pro", "Lite", "Plus", "Max", "Mini", "XL", "S", "X", "2000", "Elite", "Prime",
            "Core", "Ultimate", "Basic", "Standard", "Premium", "Commercial", "Industrial", "Home"
    };

    // Description templates
    private static final String[] DESCRIPTION_TEMPLATES = {
            "High-quality %s for professional use",
            "Affordable %s with excellent performance",
            "Premium %s with extended warranty",
            "Compact and portable %s for travel",
            "Energy-efficient %s for everyday use",
            "Advanced %s with smart features",
            "Durable %s designed for heavy usage",
            "Ergonomic %s for maximum comfort",
            "Lightweight %s perfect for mobile work",
            "Versatile %s suitable for various tasks"
    };

    public static List<Product> createTestProducts(int count) {
        List<Product> products = new ArrayList<>(count);
        var random = new Random(42); // Fixed seed for reproducibility

        for (long i = 1; i <= count; i++) {
            var name = generateProductName(random);
            var description = generateDescription(name, random);
            var price = generatePrice(random);
            var stock = generateStock(random);

            products.add(new Product(i, name, description, price, stock));
        }

        return products;
    }

    private static String generateProductName(Random random) {
        var prefix = PRODUCT_PREFIXES[random.nextInt(PRODUCT_PREFIXES.length)];
        var name = PRODUCT_NAMES[random.nextInt(PRODUCT_NAMES.length)];

        // 70% chance to have a suffix
        if (random.nextDouble() < 0.7) {
            var suffix = PRODUCT_SUFFIXES[random.nextInt(PRODUCT_SUFFIXES.length)];
            return prefix + " " + name + " " + suffix;
        } else {
            return prefix + " " + name;
        }
    }

    private static String generateDescription(String productName, Random random) {
        var template = DESCRIPTION_TEMPLATES[random.nextInt(DESCRIPTION_TEMPLATES.length)];

        var category = CATEGORIES[random.nextInt(CATEGORIES.length)];
        var wordCount = 5 + random.nextInt(15); // Between 5 and 20 additional words

        var description = new StringBuilder();
        description.append(String.format(template, productName.toLowerCase()));
        description.append(". ");
        description.append("Category: ").append(category);
        description.append(". ");

        // Add some features
        description.append("Features: ");
        int featureCount = 1 + random.nextInt(3); // 1-3 features
        for (int i = 0; i < featureCount; i++) {
            if (i > 0) description.append(", ");
            description.append(generateFeature(random));
        }

        return description.toString();
    }

    private static String generateFeature(Random random) {
        String[] features = {
                "easy to use", "energy efficient", "long battery life", "fast charging",
                "water resistant", "durable construction", "compact design", "wireless connectivity",
                "high performance", "noise reduction", "extended warranty", "adjustable settings",
                "automatic backup", "multi-user support", "cloud integration"
        };

        return features[random.nextInt(features.length)];
    }

    private static BigDecimal generatePrice(Random random) {
        // Generate prices between 9.99 and 2999.99
        double basePrice = 9.99 + (random.nextDouble() * 2990);

        // Round to 2 decimal places and common price points (e.g., X.99)
        double roundedPrice;
        if (random.nextDouble() < 0.7) {
            // 70% chance for .99 ending
            roundedPrice = Math.floor(basePrice) + 0.99;
        } else if (random.nextDouble() < 0.5) {
            // 15% chance for .50 ending
            roundedPrice = Math.floor(basePrice) + 0.5;
        } else {
            // 15% chance for .00 ending
            roundedPrice = Math.floor(basePrice);
        }

        return new BigDecimal(String.format("%.2f", roundedPrice));
    }

    private static Integer generateStock(Random random) {
        // Generate stock levels following a somewhat realistic distribution
        double randomValue = random.nextDouble();

        if (randomValue < 0.1) {
            // 10% chance of low stock (0-5)
            return random.nextInt(6);
        } else if (randomValue < 0.3) {
            // 20% chance of medium-low stock (6-20)
            return 6 + random.nextInt(15);
        } else if (randomValue < 0.7) {
            // 40% chance of medium stock (21-100)
            return 21 + random.nextInt(80);
        } else if (randomValue < 0.9) {
            // 20% chance of high stock (101-500)
            return 101 + random.nextInt(400);
        } else {
            // 10% chance of very high stock (501-2000)
            return 501 + random.nextInt(1500);
        }
    }

    // Method to get the original list of 500 products
    public static List<Product> create500Products() {
        return createTestProducts(500);
    }
}