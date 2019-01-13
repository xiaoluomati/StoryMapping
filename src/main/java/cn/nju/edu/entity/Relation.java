package cn.nju.edu.entity;

import javax.persistence.*;

@Entity
@Table(name = "role_card_relation")
public class Relation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "relation_id")
    private int relationId;

    @Column(name = "role_id")
    private int roleId;

    @Column(name = "card_id")
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
        return "Relation{" +
                "relationId=" + relationId +
                ", roleId=" + roleId +
                ", cardId=" + cardId +
                '}';
    }
}
