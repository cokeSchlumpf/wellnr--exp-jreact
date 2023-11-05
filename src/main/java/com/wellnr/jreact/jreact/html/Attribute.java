package com.wellnr.jreact.jreact.html;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(staticName = "create")
public class Attribute {

    String name;

    String value;

}
