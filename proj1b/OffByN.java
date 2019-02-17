public class OffByN implements CharacterComparator {

    private int diff;

    public OffByN(int n){
        this.diff = n;
    }
    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        if (Math.abs(diff) == this.diff) {
            return true;
        }
        return false;
    }

}