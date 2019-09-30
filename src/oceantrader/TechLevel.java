package oceantrader;

public enum TechLevel {

    PREAG {
        @Override
        public String toString() {
            return "Pre-Agriculture";
        }
    },

    AGRICULTURE {
        @Override
        public String toString() {
            return "Agriculture";
        }
    },

    MEDIEVAL {
        @Override
        public String toString() {
            return "Medieval";
        }
    },

    RENAISSANCE {
        @Override
        public String toString() {
            return "Renaissance";
        }
    },

    INDUSTRIAL {
        @Override
        public String toString() {
            return "Industrial";
        }
    },

    MODERN {
        @Override
        public String toString() {
            return "Modern";
        }
    },

    FUTURISTIC {
        @Override
        public String toString() {
            return "Futuristic";
        }
    },
}