package Scrapping;

public class NatTeamJSON {

    public rankingItem rankingItem;
    public float previousPoints;
    public tag tag;

    public String getName(){
        return this.rankingItem.name;
    }

    public String getLocation(){
        return this.tag.text;
    }

    public String getFlagSrc(){
        return this.rankingItem.flag.src;
    }

    public float getTotalPoints(){
        return this.rankingItem.totalPoints;
    }

    public String getCountryCode(){
        return this.rankingItem.countryCode;
    }

    public int getPrevRank(){
        return this.rankingItem.previousRank;
    }

    public float getPrevPoints(){
        return this.previousPoints;
    }

    public int getRank(){
        return this.rankingItem.rank;
    }


}
class tag{
    public String id,text;
}

class rankingItem{
    public String name, countryURL, countryCode;
    public flag flag;
    public float totalPoints;
    public boolean active;
    public int rank, previousRank;
}

class flag{
    public String src, title, alt;
    public int width, height;
}