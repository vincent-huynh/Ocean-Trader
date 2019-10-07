package oceantrader;

public enum ShipType {

    DEFAULT {
        @Override
        public String toString() {
            return "Row Boat";
        }
    },

    WARSHIP {
        @Override
        public String toString() {
            return "Warship";
        }
    },

    MERCHANT {
        @Override
        public String toString() {
            return "Merchant Ship";
        }
    },

    EXPLORER {
        @Override
        public String toString() {
            return "Explorer Ship";
        }
    },
}