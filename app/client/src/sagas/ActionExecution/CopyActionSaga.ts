import copy from "copy-to-clipboard";
import BizbrainzConsole from "utils/BizbrainzConsole";
import {
  ActionTriggerType,
  CopyToClipboardDescription,
} from "entities/DataTree/actionTriggers";
import { ActionValidationError } from "sagas/ActionExecution/errorUtils";
import { getType, Types } from "utils/TypeHelpers";

export default function copySaga(
  payload: CopyToClipboardDescription["payload"],
) {
  if (typeof payload.data !== "string") {
    throw new ActionValidationError(
      ActionTriggerType.COPY_TO_CLIPBOARD,
      "data",
      Types.STRING,
      getType(payload.data),
    );
  }
  const result = copy(payload.data, payload.options);
  if (result) {
    BizbrainzConsole.info({
      text: `copyToClipboard('${payload.data}') was triggered`,
    });
  }
}
