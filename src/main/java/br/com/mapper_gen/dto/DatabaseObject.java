package br.com.mapper_gen.dto;

import java.util.List;

public record DatabaseObject(
    String name,
    String owner,
    String type,
    List<Attr> attributes,
    List<Arg> arguments
) {
    public static record Attr(
        String name,
        String type,
        int dataLength,
        int dataPrecision,
        boolean nullable
    ) {}

    public static record Arg(
        String name,
        String type,
        int dataLength,
        int dataPrecision,
        boolean out,
        boolean in
    ) {}    
}

