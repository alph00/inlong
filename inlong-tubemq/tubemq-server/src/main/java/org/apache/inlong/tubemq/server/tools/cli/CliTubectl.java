/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.inlong.tubemq.server.tools.cli;

import org.apache.inlong.tubemq.corebase.utils.TStringUtils;
import org.apache.inlong.tubemq.server.common.fielddef.CliArgDef;
import org.apache.inlong.tubemq.server.common.utils.HttpUtils;

import com.google.gson.JsonObject;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is use to process CLI Tubectl process for script #{bin/tubectl.sh}.
 *
 */
public class CliTubectl extends CliAbstractBase {

    private static final Logger logger =
            LoggerFactory.getLogger(CliTubectl.class);

    private static final String defBrokerPortal = "127.0.0.1:8080";

    public CliTubectl() {
        super("tubectl.sh");
        initCommandOptions();
    }

    /**
     * Init command options
     */
    @Override
    protected void initCommandOptions() {
        // add the cli required parameters
        addCommandOption(CliArgDef.BROKERPORTAL);
        addCommandOption(CliArgDef.ADMINMETHOD);
        addCommandOption(CliArgDef.METHOD);
        addCommandOption(CliArgDef.TNAME);
        addCommandOption(CliArgDef.BROKERID);
        addCommandOption(CliArgDef.CREATEUSER);
        addCommandOption(CliArgDef.CONFMODAUTHTOKEN);
        addCommandOption(CliArgDef.MODIFYUSER);
        addCommandOption(CliArgDef.NUMPARTITIONS);
        addCommandOption(CliArgDef.DELETEPOLICY);
        addCommandOption(CliArgDef.DELETEWHEN);
        addCommandOption(CliArgDef.UNFLUSHINTERVAL);
        addCommandOption(CliArgDef.UNFLUSHTHRESHOLD);
        addCommandOption(CliArgDef.ACCEPTPUBLISH);
        addCommandOption(CliArgDef.ACCEPTSUBSCRIBE);
    }

    /**
     * Call the web query HTTP API
     * @param args     Call parameter array,
     *                 the relevant parameters are dynamic mode, which is parsed by CommandLine.
     */
    public boolean processQueryParams(String[] args) throws Exception {
        // parse parameters and check value
        CommandLine cli = parser.parse(options, args);
        if (cli == null) {
            throw new ParseException("Parse args failure");
        }
        if (cli.hasOption(CliArgDef.VERSION.longOpt)) {
            version();
        }
        if (cli.hasOption(CliArgDef.HELP.longOpt)) {
            help();
        }
        String brokerAddr = defBrokerPortal;
        if (cli.hasOption(CliArgDef.BROKERPORTAL.longOpt)) {
            brokerAddr = cli.getOptionValue(CliArgDef.BROKERPORTAL.longOpt);
            if (TStringUtils.isBlank(brokerAddr)) {
                throw new Exception(CliArgDef.BROKERPORTAL.longOpt + " is required!");
            }
        }
        JsonObject result = null;
        Map<String, String> inParamMap = new HashMap<>();
        String brokerUrl = "http://" + brokerAddr + "/webapi.htm";

        inParamMap.put("type", "op_query");
        inParamMap.put(CliArgDef.METHOD.longOpt, "admin_query_topic_info");
        if (cli.hasOption(CliArgDef.TNAME.longOpt)) {
            inParamMap.put(CliArgDef.TNAME.longOpt, cli.getOptionValue(CliArgDef.TNAME.longOpt));
        }
        if (cli.hasOption(CliArgDef.BROKERID.longOpt)) {
            inParamMap.put(CliArgDef.BROKERID.longOpt, cli.getOptionValue(CliArgDef.BROKERID.longOpt));
        }
        result = HttpUtils.requestWebService(brokerUrl, inParamMap);
        System.out.println(result.toString());
        return true;
    }
    /**
     * Call the web add HTTP API
     * @param args     Call parameter array,
     *                 the relevant parameters are dynamic mode, which is parsed by CommandLine.
     */
    public boolean processAddParams(String[] args) throws Exception {
        // parse parameters and check value
        CommandLine cli = parser.parse(options, args);
        if (cli == null) {
            throw new ParseException("Parse args failure");
        }
        if (cli.hasOption(CliArgDef.VERSION.longOpt)) {
            version();
        }
        if (cli.hasOption(CliArgDef.HELP.longOpt)) {
            help();
        }
        String brokerAddr = defBrokerPortal;
        if (cli.hasOption(CliArgDef.BROKERPORTAL.longOpt)) {
            brokerAddr = cli.getOptionValue(CliArgDef.BROKERPORTAL.longOpt);
            if (TStringUtils.isBlank(brokerAddr)) {
                throw new Exception(CliArgDef.BROKERPORTAL.longOpt + " is required!");
            }
        }
        JsonObject result = null;
        Map<String, String> inParamMap = new HashMap<>();
        String brokerUrl = "http://" + brokerAddr + "/webapi.htm";

        inParamMap.put("type", "op_modify");
        inParamMap.put(CliArgDef.METHOD.longOpt, "admin_add_new_topic_record");
        // necessary
        if (cli.hasOption(CliArgDef.TNAME.longOpt)) {
            inParamMap.put(CliArgDef.TNAME.longOpt, cli.getOptionValue(CliArgDef.TNAME.longOpt));
        } else {
            throw new Exception(CliArgDef.TNAME.longOpt + " is required!");
        }
        if (cli.hasOption(CliArgDef.CREATEUSER.longOpt)) {
            inParamMap.put(CliArgDef.CREATEUSER.longOpt, cli.getOptionValue(CliArgDef.CREATEUSER.longOpt));
        } else {
            throw new Exception(CliArgDef.CREATEUSER.longOpt + " is required!");
        }
        if (cli.hasOption(CliArgDef.CONFMODAUTHTOKEN.longOpt)) {
            inParamMap.put(CliArgDef.CONFMODAUTHTOKEN.longOpt, cli.getOptionValue(CliArgDef.CONFMODAUTHTOKEN.longOpt));
        } else {
            throw new Exception(CliArgDef.CONFMODAUTHTOKEN.longOpt + " is required!");
        }
        if (cli.hasOption(CliArgDef.BROKERID.longOpt)) {
            inParamMap.put(CliArgDef.BROKERID.longOpt, cli.getOptionValue(CliArgDef.BROKERID.longOpt));
        } else {
            throw new Exception(CliArgDef.BROKERID.longOpt + " is required!");
        }
        // non necessary
        if (cli.hasOption(CliArgDef.DELETEWHEN.longOpt)) {
            inParamMap.put(CliArgDef.DELETEWHEN.longOpt, cli.getOptionValue(CliArgDef.DELETEWHEN.longOpt));
        }
        if (cli.hasOption(CliArgDef.DELETEPOLICY.longOpt)) {
            inParamMap.put(CliArgDef.DELETEPOLICY.longOpt, cli.getOptionValue(CliArgDef.DELETEPOLICY.longOpt));
        }
        if (cli.hasOption(CliArgDef.NUMPARTITIONS.longOpt)) {
            inParamMap.put(CliArgDef.NUMPARTITIONS.longOpt, cli.getOptionValue(CliArgDef.NUMPARTITIONS.longOpt));
        }
        if (cli.hasOption(CliArgDef.UNFLUSHTHRESHOLD.longOpt)) {
            inParamMap.put(CliArgDef.UNFLUSHTHRESHOLD.longOpt, cli.getOptionValue(CliArgDef.UNFLUSHTHRESHOLD.longOpt));
        }
        if (cli.hasOption(CliArgDef.UNFLUSHINTERVAL.longOpt)) {
            inParamMap.put(CliArgDef.UNFLUSHINTERVAL.longOpt, cli.getOptionValue(CliArgDef.UNFLUSHINTERVAL.longOpt));
        }
        if (cli.hasOption(CliArgDef.ACCEPTPUBLISH.longOpt)) {
            inParamMap.put(CliArgDef.ACCEPTPUBLISH.longOpt, cli.getOptionValue(CliArgDef.ACCEPTPUBLISH.longOpt));
        }
        if (cli.hasOption(CliArgDef.ACCEPTSUBSCRIBE.longOpt)) {
            inParamMap.put(CliArgDef.ACCEPTSUBSCRIBE.longOpt, cli.getOptionValue(CliArgDef.ACCEPTSUBSCRIBE.longOpt));
        }

        result = HttpUtils.requestWebService(brokerUrl, inParamMap);
        System.out.println(result.toString());
        return true;
    }
    /**
     * Call the web modify HTTP API
     * @param args     Call parameter array,
     *                 the relevant parameters are dynamic mode, which is parsed by CommandLine.
     */
    public boolean processModifyParams(String[] args) throws Exception {
        // parse parameters and check value
        CommandLine cli = parser.parse(options, args);
        if (cli == null) {
            throw new ParseException("Parse args failure");
        }
        if (cli.hasOption(CliArgDef.VERSION.longOpt)) {
            version();
        }
        if (cli.hasOption(CliArgDef.HELP.longOpt)) {
            help();
        }
        String brokerAddr = defBrokerPortal;
        if (cli.hasOption(CliArgDef.BROKERPORTAL.longOpt)) {
            brokerAddr = cli.getOptionValue(CliArgDef.BROKERPORTAL.longOpt);
            if (TStringUtils.isBlank(brokerAddr)) {
                throw new Exception(CliArgDef.BROKERPORTAL.longOpt + " is required!");
            }
        }
        JsonObject result = null;
        Map<String, String> inParamMap = new HashMap<>();
        String brokerUrl = "http://" + brokerAddr + "/webapi.htm";

        inParamMap.put("type", "op_modify");
        inParamMap.put(CliArgDef.METHOD.longOpt, "admin_modify_topic_info");
        // necessary
        if (cli.hasOption(CliArgDef.TNAME.longOpt)) {
            inParamMap.put(CliArgDef.TNAME.longOpt, cli.getOptionValue(CliArgDef.TNAME.longOpt));
        } else {
            throw new Exception(CliArgDef.TNAME.longOpt + " is required!");
        }
        if (cli.hasOption(CliArgDef.BROKERID.longOpt)) {
            inParamMap.put(CliArgDef.BROKERID.longOpt, cli.getOptionValue(CliArgDef.BROKERID.longOpt));
        } else {
            throw new Exception(CliArgDef.BROKERID.longOpt + " is required!");
        }
        if (cli.hasOption(CliArgDef.MODIFYUSER.longOpt)) {
            inParamMap.put(CliArgDef.MODIFYUSER.longOpt, cli.getOptionValue(CliArgDef.MODIFYUSER.longOpt));
        } else {
            throw new Exception(CliArgDef.MODIFYUSER.longOpt + " is required!");
        }
        if (cli.hasOption(CliArgDef.CONFMODAUTHTOKEN.longOpt)) {
            inParamMap.put(CliArgDef.CONFMODAUTHTOKEN.longOpt, cli.getOptionValue(CliArgDef.CONFMODAUTHTOKEN.longOpt));
        } else {
            throw new Exception(CliArgDef.CONFMODAUTHTOKEN.longOpt + " is required!");
        }
        // non necessary
        if (cli.hasOption(CliArgDef.DELETEWHEN.longOpt)) {
            inParamMap.put(CliArgDef.DELETEWHEN.longOpt, cli.getOptionValue(CliArgDef.DELETEWHEN.longOpt));
        }
        if (cli.hasOption(CliArgDef.DELETEPOLICY.longOpt)) {
            inParamMap.put(CliArgDef.DELETEPOLICY.longOpt, cli.getOptionValue(CliArgDef.DELETEPOLICY.longOpt));
        }
        if (cli.hasOption(CliArgDef.NUMPARTITIONS.longOpt)) {
            inParamMap.put(CliArgDef.NUMPARTITIONS.longOpt, cli.getOptionValue(CliArgDef.NUMPARTITIONS.longOpt));
        }
        if (cli.hasOption(CliArgDef.UNFLUSHTHRESHOLD.longOpt)) {
            inParamMap.put(CliArgDef.UNFLUSHTHRESHOLD.longOpt, cli.getOptionValue(CliArgDef.UNFLUSHTHRESHOLD.longOpt));
        }
        if (cli.hasOption(CliArgDef.UNFLUSHINTERVAL.longOpt)) {
            inParamMap.put(CliArgDef.UNFLUSHINTERVAL.longOpt, cli.getOptionValue(CliArgDef.UNFLUSHINTERVAL.longOpt));
        }
        if (cli.hasOption(CliArgDef.ACCEPTPUBLISH.longOpt)) {
            inParamMap.put(CliArgDef.ACCEPTPUBLISH.longOpt, cli.getOptionValue(CliArgDef.ACCEPTPUBLISH.longOpt));
        }
        if (cli.hasOption(CliArgDef.ACCEPTSUBSCRIBE.longOpt)) {
            inParamMap.put(CliArgDef.ACCEPTSUBSCRIBE.longOpt, cli.getOptionValue(CliArgDef.ACCEPTSUBSCRIBE.longOpt));
        }

        result = HttpUtils.requestWebService(brokerUrl, inParamMap);
        System.out.println(result.toString());
        return true;
    }
    /**
     * Call the web delete HTTP API
     * @param args     Call parameter array,
     *                 the relevant parameters are dynamic mode, which is parsed by CommandLine.
     */
    public boolean processDeleteParams(String[] args) throws Exception {
        // parse parameters and check value
        CommandLine cli = parser.parse(options, args);
        if (cli == null) {
            throw new ParseException("Parse args failure");
        }
        if (cli.hasOption(CliArgDef.VERSION.longOpt)) {
            version();
        }
        if (cli.hasOption(CliArgDef.HELP.longOpt)) {
            help();
        }
        String brokerAddr = defBrokerPortal;
        if (cli.hasOption(CliArgDef.BROKERPORTAL.longOpt)) {
            brokerAddr = cli.getOptionValue(CliArgDef.BROKERPORTAL.longOpt);
            if (TStringUtils.isBlank(brokerAddr)) {
                throw new Exception(CliArgDef.BROKERPORTAL.longOpt + " is required!");
            }
        }
        JsonObject result = null;
        Map<String, String> inParamMap = new HashMap<>();
        String brokerUrl = "http://" + brokerAddr + "/webapi.htm";

        inParamMap.put("type", "op_modify");
        inParamMap.put(CliArgDef.METHOD.longOpt, "admin_delete_topic_info");
        // necessary
        if (cli.hasOption(CliArgDef.TNAME.longOpt)) {
            inParamMap.put(CliArgDef.TNAME.longOpt, cli.getOptionValue(CliArgDef.TNAME.longOpt));
        } else {
            throw new Exception(CliArgDef.TNAME.longOpt + " is required!");
        }
        if (cli.hasOption(CliArgDef.BROKERID.longOpt)) {
            inParamMap.put(CliArgDef.BROKERID.longOpt, cli.getOptionValue(CliArgDef.BROKERID.longOpt));
        } else {
            throw new Exception(CliArgDef.BROKERID.longOpt + " is required!");
        }
        if (cli.hasOption(CliArgDef.MODIFYUSER.longOpt)) {
            inParamMap.put(CliArgDef.MODIFYUSER.longOpt, cli.getOptionValue(CliArgDef.MODIFYUSER.longOpt));
        } else {
            throw new Exception(CliArgDef.MODIFYUSER.longOpt + " is required!");
        }
        if (cli.hasOption(CliArgDef.CONFMODAUTHTOKEN.longOpt)) {
            inParamMap.put(CliArgDef.CONFMODAUTHTOKEN.longOpt, cli.getOptionValue(CliArgDef.CONFMODAUTHTOKEN.longOpt));
        } else {
            throw new Exception(CliArgDef.CONFMODAUTHTOKEN.longOpt + " is required!");
        }

        result = HttpUtils.requestWebService(brokerUrl, inParamMap);
        System.out.println(result.toString());
        return true;
    }

    /**
     * Call the web HTTP API by the tubectl.sh script
     * @param args     Call parameter array,
     *                 the relevant parameters are dynamic mode, which is parsed by CommandLine.
     */
    @Override
    public boolean processParams(String[] args) throws Exception {
        if (args.length > 0) {
            if (args[0].equals("help")) {
                this.help();
            } else if (args[0].equals("query")) {
                return this.processQueryParams(Arrays.copyOfRange(args, 1, args.length));
            } else if (args[0].equals("add")) {
                return this.processAddParams(Arrays.copyOfRange(args, 1, args.length));
            } else if (args[0].equals("modify")) {
                return this.processModifyParams(Arrays.copyOfRange(args, 1, args.length));
            } else if (args[0].equals("delete")) {
                return this.processDeleteParams(Arrays.copyOfRange(args, 1, args.length));
            }
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        CliTubectl cliTubectl = new CliTubectl();
        try {
            cliTubectl.processParams(args);
        } catch (Throwable ex) {
            ex.printStackTrace();
            logger.error(ex.getMessage());
            cliTubectl.help();
        }
    }

}