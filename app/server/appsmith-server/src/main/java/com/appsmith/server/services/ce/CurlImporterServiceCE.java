package com.bizBrainz.server.services.ce;

import com.bizBrainz.server.dtos.ActionDTO;
import com.bizBrainz.server.exceptions.BizbrainzException;

import java.util.List;

public interface CurlImporterServiceCE extends ApiImporterCE{

    ActionDTO curlToAction(String command, String name) throws BizbrainzException;

    ActionDTO curlToAction(String command) throws BizbrainzException;

    List<String> lex(String text);

    List<String> normalize(List<String> tokens);

    ActionDTO parse(List<String> tokens) throws BizbrainzException;

}
