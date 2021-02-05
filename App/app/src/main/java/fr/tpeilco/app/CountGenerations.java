package fr.tpeilco.app;

public class CountGenerations {
    private int gen1;
    private int gen2;
    private int gen3;
    private int gen4;
    private int gen5;
    private int gen6;
    private int gen7;
    private int gen8;
    private int total;

    public CountGenerations(int gen1, int gen2, int gen3, int gen4, int gen5, int gen6, int gen7, int total) {
        this.gen1 = gen1;
        this.gen2 = gen2;
        this.gen3 = gen3;
        this.gen4 = gen4;
        this.gen5 = gen5;
        this.gen6 = gen6;
        this.gen7 = gen7;
        this.gen8 = 999999;
        this.total = total;
    }

    public int getOffset(String name){
        int offset;
        switch(name) {
            case "generation 1": offset = 0; break;
            case "generation 2" : offset = this.getGen1(); break;
            case "generation 3" : offset = this.getGen1() + this.getGen2(); break;
            case "generation 4" : offset = this.getGen1() + this.getGen2() + this.getGen3(); break;
            case "generation 5" : offset = this.getGen1() + this.getGen2() + this.getGen3() + this.getGen4(); break;
            case "generation 6" : offset = this.getGen1() + this.getGen2() + this.getGen3() + this.getGen4() + this.getGen5(); break;
            case "generation 7" : offset = this.getGen1() + this.getGen2() + this.getGen3() + this.getGen4() + this.getGen5() + this.getGen6(); break;
            case "generation 8" : offset = this.total; break;
            default: offset = 0;
        }

        return offset;

    }

    public int getLimit(String name){
        int limit;
        switch(name) {
            case "generation 1": limit = this.getGen1(); break;
            case "generation 2" : limit = this.getGen2(); break;
            case "generation 3" : limit =  this.getGen3(); break;
            case "generation 4" : limit =  this.getGen4(); break;
            case "generation 5" : limit = this.getGen5(); break;
            case "generation 6" : limit = this.getGen6(); break;
            case "generation 7" : limit =  this.getGen7(); break;
            case "generation 8" : limit = this.getGen8(); break;
            default: limit = 0;
        }

        return limit;

    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getGen1() {
        return gen1;
    }

    public void setGen1(int gen1) {
        this.gen1 = gen1;
    }

    public int getGen2() {
        return gen2;
    }

    public void setGen2(int gen2) {
        this.gen2 = gen2;
    }

    public int getGen3() {
        return gen3;
    }

    public void setGen3(int gen3) {
        this.gen3 = gen3;
    }

    public int getGen4() {
        return gen4;
    }

    public void setGen4(int gen4) {
        this.gen4 = gen4;
    }

    public int getGen5() {
        return gen5;
    }

    public void setGen5(int gen5) {
        this.gen5 = gen5;
    }

    public int getGen6() {
        return gen6;
    }

    public void setGen6(int gen6) {
        this.gen6 = gen6;
    }

    public int getGen7() {
        return gen7;
    }

    public void setGen7(int gen7) {
        this.gen7 = gen7;
    }

    public int getGen8() {
        return gen8;
    }

    public void setGen8(int gen8) {
        this.gen8 = gen8;
    }
}
