package com.delazeri.music.dtos;

public record Response<T>(Boolean success,T data){
}
