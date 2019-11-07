public class Pick implements Comparable<Pick> {

    public Integer round;
    public String originalTeam;
    public Integer year;

    public Pick(int round, String originalTeam, int year){

        this.round = round;
        this.originalTeam = originalTeam;
        this.year = year;

    }

    public int compareTo(Pick o) {
        int result = this.year.compareTo(o.year);
        if(result==0) {
            return this.round.compareTo(o.round);
        }
        else {
            return result;
        }
    }

    public String toString(){
        String result = "Round: "+this.round+" - "+this.year+" - "+this.originalTeam+"";
        return result;
    }
}
