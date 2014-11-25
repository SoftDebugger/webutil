package cn.buglife.data.access.model;

import cn.buglife.data.framework.HandleAnnotation;
import cn.buglife.data.framework.annotation.Column;
import cn.buglife.data.framework.annotation.Table;

/**
 * Created by CrazyHarry on 2014/11/25.
 */
@Table(name="member")
public class Member {

    @Column(name = "id",length = 16,value = "")
    public int id;

    @Column(name = "name",length = 12,value = "")
    public String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String args[]){
        HandleAnnotation.read(Member.class);
    }
}
