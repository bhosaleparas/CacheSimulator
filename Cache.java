public interface Cache {
    void accessData(String data);
    boolean contains(String data);
    void display();
    void reset();
    int getHits();
    int getMisses();
    double getHitRatio();
    int getSize();
    int getCapacity();
}