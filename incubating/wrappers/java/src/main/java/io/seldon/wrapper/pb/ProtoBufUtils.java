/**
 * ***************************************************************************** 
 * 
 * Copyright (c) 2024 Seldon Technologies Ltd.
 * 
 * Use of this software is governed BY
 * (1) the license included in the LICENSE file or
 * (2) if the license included in the LICENSE file is the Business Source License 1.1,
 * the Change License after the Change Date as each is defined in accordance with the LICENSE file.
 *
 * *****************************************************************************
 */
package io.seldon.wrapper.pb;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.util.JsonFormat;
import com.google.protobuf.util.JsonFormat.Printer;

public class ProtoBufUtils {

  private ProtoBufUtils() {}

  /**
   * Serialize a protobuf message to JSON, indicating if whitespace needs to be stripped.
   *
   * @param message The protobuf message to serialize.
   * @param omittingInsignificantWhitespace True if needs to be stripped of whitespace.
   * @return json string
   * @throws InvalidProtocolBufferException
   */
  public static String toJson(Message message, boolean omittingInsignificantWhitespace)
      throws InvalidProtocolBufferException {
    String json = null;
    // json =
    // JsonFormat.printer().includingDefaultValueFields().preservingProtoFieldNames().print(message);
    // json =
    // JsonFormat.printer().includingDefaultValueFields().preservingProtoFieldNames().omittingInsignificantWhitespace().print(message);

    Printer jsonPrinter =
        JsonFormat.printer().includingDefaultValueFields().preservingProtoFieldNames();
    if (omittingInsignificantWhitespace) {
      jsonPrinter = jsonPrinter.omittingInsignificantWhitespace();
    }
    return jsonPrinter.print(message);
  }

  /**
   * Serialize a protobuf message to JSON, allowing the inclusion of whitespace.
   *
   * @param message The protobuf message to serialize.
   * @return json string
   * @throws InvalidProtocolBufferException
   */
  public static String toJson(Message message) throws InvalidProtocolBufferException {
    boolean omittingInsignificantWhitespace = false;
    return toJson(message, omittingInsignificantWhitespace);
  }

  public static <T extends Message.Builder> void updateMessageBuilderFromJson(
      T messageBuilder, String json) throws InvalidProtocolBufferException {
    JsonFormat.parser().ignoringUnknownFields().merge(json, messageBuilder);
  }
}
