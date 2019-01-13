package cn.nju.edu.vo;

public class RelationVo {
    private int relationId;
    private int roleId;
    private int cardId;

    public int getRelationId() {
        return relationId;
    }

    public void setRelationId(int relationId) {
        this.relationId = relationId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    @Override
    public String toString() {
        return "RelationVo{" +
                "relationId=" + relationId +
                ", roleId=" + roleId +
                ", cardId=" + cardId +
                '}';
    }
}
