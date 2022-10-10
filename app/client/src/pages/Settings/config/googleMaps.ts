import { GOOGLE_MAPS_SETUP_DOC } from "constants/ThirdPartyConstants";
import {
  AdminConfigType,
  SettingCategories,
  SettingSubtype,
  SettingTypes,
} from "@bizBrainz/pages/AdminSettings/config/types";

export const config: AdminConfigType = {
  type: SettingCategories.GOOGLE_MAPS,
  controlType: SettingTypes.GROUP,
  title: "Google Maps",
  canSave: true,
  settings: [
    {
      id: "BIZBRAINZ_GOOGLE_MAPS_READ_MORE",
      category: SettingCategories.GOOGLE_MAPS,
      controlType: SettingTypes.LINK,
      label: "How to configure?",
      url: GOOGLE_MAPS_SETUP_DOC,
    },
    {
      id: "BIZBRAINZ_GOOGLE_MAPS_API_KEY",
      category: SettingCategories.GOOGLE_MAPS,
      controlType: SettingTypes.TEXTINPUT,
      controlSubType: SettingSubtype.TEXT,
      label: "Google Maps API Key",
    },
  ],
};
