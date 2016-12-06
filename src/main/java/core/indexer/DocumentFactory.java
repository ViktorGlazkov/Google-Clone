package core.indexer;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.IndexOptions;

public class DocumentFactory {

    public static Document createWith(final String titleStr, final String bodyStr, String url) {
        Document document = new Document();

        FieldType textIndexedType = new FieldType();
        textIndexedType.setStored(true);
        textIndexedType.setIndexOptions(IndexOptions.DOCS);
        textIndexedType.setTokenized(true);

        Field title = new Field("title", titleStr, textIndexedType);
        Field body = new Field("body", bodyStr, textIndexedType);
        Field link = new Field("link", url, textIndexedType);

        document.add(title);
        document.add(body);
        document.add(link);
        return document;
    }
}
