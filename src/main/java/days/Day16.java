package days;

import java.util.*;

public class Day16 extends Day {
    static Map<String, String> dictionary = new HashMap<String, String>() {{
        put("0", "0000");
        put("1", "0001");
        put("2", "0010");
        put("3", "0011");
        put("4", "0100");
        put("5", "0101");
        put("6", "0110");
        put("7", "0111");
        put("8", "1000");
        put("9", "1001");
        put("A", "1010");
        put("B", "1011");
        put("C", "1100");
        put("D", "1101");
        put("E", "1110");
        put("F", "1111");

    }};

    final static int BITS_VERSION = 3;
    final static int BITS_TYPE_ID = 3;

    static class Packet{
        public static final int LITERAL_TYPE = 4;
        public static final int LENGTH_TYPE_ZERO = 15;
        public static final int LENGTH_TYPE_ONE = 11;
        public static final int LITERAL_BIT_LENGTH = 4;
        String binary;
        int version;
        int typeId;
        long literalValue;
        int currentIndex=0;
        List<Packet> packets= new ArrayList<>();

        public Packet(String binary) {
            this.binary = binary;

            parse();
        }

        public Packet(String binary, int index){
            this.binary=binary;
            this.currentIndex = index;
            parse();
        }

        private void parse() {
            this.version = readBitsAsInt(BITS_VERSION);
            this.typeId = readBitsAsInt(BITS_TYPE_ID);

            if(this.typeId== LITERAL_TYPE){
                this.parseLiteralValue();
            }else{
                this.parseOperator();
            }
        }

        private void parseOperator() {
            int lengthTypeID = this.readBitsAsInt(1);
            if(lengthTypeID==0){
                int endIndex = this.readBitsAsInt(LENGTH_TYPE_ZERO)+this.currentIndex;
                while (this.currentIndex<endIndex){
                    this.parseNextPacket();
                }
            }else{
                int count = this.readBitsAsInt(LENGTH_TYPE_ONE);
                for (int i = 0; i < count; i++) {
                    this.parseNextPacket();
                }
            }

        }

        private void parseNextPacket() {
            Packet packet = new Packet(this.binary,this.currentIndex);
            packets.add(packet);
            this.currentIndex =packet.getIndex();
        }

        private void parseLiteralValue() {
            StringBuilder literal = new StringBuilder();
            boolean lastGroup;
            do {
                lastGroup = readBits(1).equals("0");
                literal.append(readBits(LITERAL_BIT_LENGTH));
            } while (!lastGroup);

            this.literalValue = Long.parseLong(literal.toString(),2);
        }

        private int readBitsAsInt(int n) {
            return Integer.parseInt(readBits(n),2);
        }

        private String readBits(int n){
            String bits = this.binary.substring(this.currentIndex,this.currentIndex+n);
            this.currentIndex+=n;
            return bits;
        }

        public int getIndex(){
            return this.currentIndex;
        }

        public int getVersion() {
            int sum = version;
            for (Packet p: packets
                 ) {
                sum+=p.getVersion();
            }
            return sum;
        }

        public long getValue(){
            switch (typeId) {
                case 0:
                    return packets.stream().mapToLong(Packet::getValue).reduce(Long::sum).getAsLong();
                case 1:
                    return packets.stream().mapToLong(Packet::getValue).reduce((acc, item)->acc*item).getAsLong();
                case 2:
                    return packets.stream().mapToLong(Packet::getValue).min().getAsLong();
                case 3:
                    return packets.stream().mapToLong(Packet::getValue).max().getAsLong();
                case 4:
                    return literalValue;
                case 5:
                   return packets.get(0).getValue()>packets.get(1).getValue()?1:0;
                case 6:
                    return packets.get(0).getValue()<packets.get(1).getValue()?1:0;
                case 7:
                    return packets.get(0).getValue()==packets.get(1).getValue()?1:0;
                default:
                    return 0;
            }

        }

        @Override
        public String toString() {
            return "Packet{" +
                    "binary='" + binary + '\'' +
                    ", version=" + version +
                    ", typeId=" + typeId +
                    ", literalValue=" + literalValue +
                    ", packets=" + packets +
                    '}';
        }
    }

    public Day16(Boolean isTest) {
        super(isTest);
    }

    public String part1() {
        String[] input = this.getInput().split("\r\n");
        Packet packet = new Packet(getBinary(input[0]));

        int result = packet.getVersion();
        System.out.printf("Version Sum: %d%n", result);
        return String.valueOf(result);
    }

    private String getBinary(String transmission) {
        StringBuilder binary = new StringBuilder();
        for (int i = 0; i < transmission.length(); i++) {
            binary.append(dictionary.get(transmission.substring(i, i + 1)));
        }

        return binary.toString();
    }

    public String part2() {
        String[] input = this.getInput().split("\r\n");
        Packet packet = new Packet(getBinary(input[0]));

        long result = packet.getValue();
        System.out.printf("Packet Value: %d%n", result);
        return String.valueOf(result);
    }
}