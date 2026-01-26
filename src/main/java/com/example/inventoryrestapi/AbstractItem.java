package com.example.inventoryrestapi;

import java.util.Objects;
public abstract class AbstractItem {

    private int id;

    public AbstractItem(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public abstract String shortInfo();

    public boolean equals(Object o){
        if(this == o)
            return true;
        if(o == null)
            return false;

        AbstractItem otherItem = (AbstractItem) o;
        return this.id == otherItem.id;
    }

    public int hashCode(){
        return Objects.hash(id);
    }
}
