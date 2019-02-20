package cn.think.in.java.learing.protobuf;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

/**
 *
 * @author 莫那·鲁道
 * @date 2019-02-20-14:09
 */
public class ProtobufUtil {

    static <T> byte[] serializer(Object o) {

        Schema schema = RuntimeSchema.getSchema(o.getClass());
        byte[] bytes = ProtobufIOUtil
            .toByteArray(o, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
        return bytes;

    }

    static <T> T deSerializer(byte[] bytes, Class<T> clazz) {
        Object obj = null;
        try {
            obj = clazz.newInstance();
            Schema schema = RuntimeSchema.getSchema(obj.getClass());
            ProtostuffIOUtil.mergeFrom(bytes, obj, schema);
        } catch (Exception e) {

        }
        return (T) obj;
    }

    static AllTypeObj person = new AllTypeObj();

    public static void main(String args[]) {
        byte[] b = ProtobufUtil.serializer(person);

        AllTypeObj a = ProtobufUtil.deSerializer(b, AllTypeObj.class);
        System.out.println();
    }

}
