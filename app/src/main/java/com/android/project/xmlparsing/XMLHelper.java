package com.android.project.xmlparsing;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class XMLHelper extends DefaultHandler {

    private String URL = "https://api.androidhive.info/pizza/?format=xml";
    String TAG = "XMLHelper";
    Boolean currTAG = false;
    String currTagVal = "";
    public PostValue postValue = null;

    public ArrayList<PostValue> postValueArrayList;

    public void get(){
        try{

            SAXParserFactory factory =SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLReader reader = parser.getXMLReader();
            reader.setContentHandler(this);
            URL url = new URL(URL);
            InputStream stream = url.openStream();
            reader.parse(new InputSource(stream));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currTAG = true;
        currTagVal = "";

        if(localName.equals("menu")){
            postValueArrayList = new ArrayList<>();
        }

        if(qName.equals("item")) {
            postValue = new PostValue();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        currTAG = false;

        if(qName.equals("id")) {
            postValue.setId(currTagVal);
        } else if(qName.equals("name")) {
            postValue.setName(currTagVal);
        } else if(qName.equals("cost")) {
            postValue.setCost(currTagVal);
        } else if(qName.equals("description")) {
            postValue.setDescription(currTagVal);
        } else if (qName.equals("item")) {
            postValueArrayList.add(postValue);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        if(currTAG){
            currTagVal = currTagVal + new String(ch,start,length);
            currTAG = false;
        }
    }
}
