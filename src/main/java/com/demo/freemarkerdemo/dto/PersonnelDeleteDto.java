package com.demo.freemarkerdemo.dto;
import lombok.Data;


@Data
public class PersonnelDeleteDto {

        /**
        * 
        */
        private Integer id;
        /**
        * 
        */
        private String name;

    private int operationType;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
            sb.append(", id=").append(id);
            sb.append(", name=").append(name);
        sb.append("]");
        return sb.toString();
    }

}