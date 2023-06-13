package org.zerock.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class PageRequestDTO {

    @Builder.Default
    private int page = 1;
    @Builder.Default
    private int size = 10;

    public void setPage(int page){
        if(page <= 0){
            this.page = 1;
        }else {
            this.page = page;
        }
    }

    public void setSize(int size){
        if(size > 100 || size < 0){
            this.size = 10;
        }else {
            this.size = size;
        }
    }

    public int getSkip(){
        return (this.page-1) * this.size;
    }

    public int getEnd(){
        return this.page * this.size;
    }

    public int getCountEnd(){

        int temp = (int)(Math.ceil(this.page/10.0)) * (10*size);

        return temp+1;
    }
}
