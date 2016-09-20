package com.hyulib.login.portaluser;

import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Converter {
    private Marshaller marshaller;
    private Unmarshaller unmarshaller;

    public void setMarshaller(Marshaller marshaller) {
        this.marshaller = marshaller;
    }

    public void setUnmarshaller(Unmarshaller unmarshaller) {
        this.unmarshaller = unmarshaller;
    }
    //Converts Object to XML file
    public void doMarshaling(String fileName, Object graph) throws IOException {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fileName);
            marshaller.marshal(graph, new StreamResult(fos));
        } finally {
        	fos.close();
        }
    }
    //Converts XML File to Java Object
    public Object doUnMarshaling(String fileName) throws IOException {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(fileName);
            return unmarshaller.unmarshal(new StreamSource(fis));
        } finally {
        	fis.close();
        }
    }
    //Converts XML Stream to Java Object
    public Object doUnMarshaling(InputStream inputStream) throws IOException{

             return unmarshaller.unmarshal(new StreamSource(inputStream));

    }
}