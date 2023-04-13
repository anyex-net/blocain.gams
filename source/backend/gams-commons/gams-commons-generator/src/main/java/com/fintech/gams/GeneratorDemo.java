package com.fintech.gams;

/**
 * com.fintech.gams.generator.GeneratorDemo Introduce
 * <p>File：com.fintech.gams.generator.GeneratorDemo.java </p>
 * <p>Title: com.fintech.gams.generator.GeneratorDemo </p>
 * <p>Description:com.fintech.gams.generator.GeneratorDemo </p>
 * <p>Copyright: Copyright (c) 17/6/16</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
public class GeneratorDemo
{
    public static void main(String[] args) throws Exception
    {
        GeneratorFile file = new GeneratorFile();
        file.generateCRUDByTables(new String[]{"OAUTH_CODE","ACCESS_TOKEN","CLIENT_DETAILS"});
    }
}
