class Pair<T1, T2> {
    private final T1 key;
    private final T2 value;

    public Pair(T1 move, T2 utility) {
        this.key = move;
        this.value = utility;
    }

    public T1 getMove() {
        return key;
    }

    public T2 getValue() {
        return value;
    }

}
