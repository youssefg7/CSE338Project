package Scrapping;

public class Item {

    public rankingItem rankingItem;
    public float previousPoints;
    public tag tag;

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