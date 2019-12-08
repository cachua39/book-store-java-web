/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luonglv.dtos;

import java.io.Serializable;

/**
 *
 * @author LeVaLu
 */
public class BookError implements Serializable{
    private String bookIdError;

    public BookError() {
    }

    public String getBookIdError() {
        return bookIdError;
    }

    public void setBookIdError(String bookIdError) {
        this.bookIdError = bookIdError;
    }
    
}
