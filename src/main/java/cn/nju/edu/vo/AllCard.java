package cn.nju.edu.vo;

import java.util.List;

public class AllCard {

    private int mapLong;

    private int mapWide;

    private List<CardVo> cardVos;

    public AllCard(int mapLong, int mapWide, List<CardVo> cardVos) {
        this.mapLong = mapLong;
        this.mapWide = mapWide;
        this.cardVos = cardVos;
    }

    public int getMapLong() {
        return mapLong;
    }

    public void setMapLong(int mapLong) {
        this.mapLong = mapLong;
    }

    public int getMapWide() {
        return mapWide;
    }

    public void setMapWide(int mapWide) {
        this.mapWide = mapWide;
    }

    public List<CardVo> getCardVos() {
        return cardVos;
    }

    public void setCardVos(List<CardVo> cardVos) {
        this.cardVos = cardVos;
    }

    @Override
    public String toString() {
        return "AllCard{" +
                "mapLong='" + mapLong + '\'' +
                ", mapWide='" + mapWide + '\'' +
                ", cardList='" + cardVos + '\'' +
                '}';
    }
}
