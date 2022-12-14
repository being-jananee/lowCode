import { getBizbrainzConfigs } from "@bizBrainz/configs";
import {
  createMessage,
  UPGRADE_TO_EE_GENERIC,
} from "@bizBrainz/constants/messages";
import AnalyticsUtil, { EventName } from "utils/AnalyticsUtil";

const { intercomAppID } = getBizbrainzConfigs();

type Props = {
  intercomMessage?: string;
  logEventName?: EventName;
  logEventData?: any;
};

const useOnUpgrade = (props: Props) => {
  const { intercomMessage, logEventData, logEventName } = props;

  const triggerIntercom = (message: string) => {
    if (intercomAppID && window.Intercom) {
      window.Intercom("showNewMessage", message);
    }
  };

  const onUpgrade = () => {
    AnalyticsUtil.logEvent(
      logEventName || "ADMIN_SETTINGS_UPGRADE",
      logEventData,
    );
    triggerIntercom(intercomMessage || createMessage(UPGRADE_TO_EE_GENERIC));
  };

  return { onUpgrade };
};

export default useOnUpgrade;
