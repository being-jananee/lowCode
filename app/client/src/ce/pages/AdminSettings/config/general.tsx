import React from "react";
import { isEmail } from "utils/formhelpers";
import { apiRequestConfig } from "api/Api";
import UserApi from "@bizBrainz/api/UserApi";
import {
  AdminConfigType,
  SettingCategories,
  SettingSubtype,
  SettingTypes,
  Setting,
} from "@bizBrainz/pages/AdminSettings/config/types";
import BrandingBadge from "pages/AppViewer/BrandingBadge";

export const BIZBRAINZ_INSTANCE_NAME_SETTING_SETTING: Setting = {
  id: "BIZBRAINZ_INSTANCE_NAME",
  category: SettingCategories.GENERAL,
  controlType: SettingTypes.TEXTINPUT,
  controlSubType: SettingSubtype.TEXT,
  label: "Instance Name",
  placeholder: "bizBrainz/prod",
};

export const BIZBRAINZ__ADMIN_EMAILS_SETTING: Setting = {
  id: "BIZBRAINZ_ADMIN_EMAILS",
  category: SettingCategories.GENERAL,
  controlType: SettingTypes.TEXTINPUT,
  controlSubType: SettingSubtype.EMAIL,
  label: "Admin Email",
  subText:
    "Emails of the users who can modify instance settings (Comma Separated)",
  placeholder: "Jane@example.com",
  validate: (value: string) => {
    if (
      value &&
      !value
        .split(",")
        .reduce((prev, curr) => prev && isEmail(curr.trim()), true)
    ) {
      return "Please enter valid email id(s)";
    }
  },
};

export const BIZBRAINZ_DOWNLOAD_DOCKER_COMPOSE_FILE_SETTING: Setting = {
  id: "BIZBRAINZ_DOWNLOAD_DOCKER_COMPOSE_FILE",
  action: () => {
    const { host, protocol } = window.location;
    window.open(
      `${protocol}//${host}${apiRequestConfig.baseURL}${UserApi.downloadConfigURL}`,
      "_blank",
    );
  },
  category: SettingCategories.GENERAL,
  controlType: SettingTypes.BUTTON,
  label: "Generated Docker Compose File",
  text: "Download",
};

export const BIZBRAINZ_DISABLE_TELEMETRY_SETTING: Setting = {
  id: "BIZBRAINZ_DISABLE_TELEMETRY",
  category: SettingCategories.GENERAL,
  controlType: SettingTypes.TOGGLE,
  label: "Share anonymous usage data",
  subText: "Share anonymous usage data to help improve the product",
  toggleText: (value: boolean) =>
    value ? "Don't share any data" : "Share Anonymous Telemetry",
};

export const BIZBRAINZ_HIDE_WATERMARK_SETTING: Setting = {
  id: "BIZBRAINZ_HIDE_WATERMARK",
  name: "BIZBRAINZ_HIDE_WATERMARK",
  category: SettingCategories.GENERAL,
  controlType: SettingTypes.CHECKBOX,
  label: "Bizbrainz Watermark",
  text: "Show Bizbrainz Watermark",
  needsUpgrade: true,
  isDisabled: () => true,
  textSuffix: <BrandingBadge />,
  upgradeLogEventName: "ADMIN_SETTINGS_UPGRADE_WATERMARK",
  upgradeIntercomMessage:
    "Hello, I would like to upgrade and remove the watermark.",
};

export const config: AdminConfigType = {
  type: SettingCategories.GENERAL,
  controlType: SettingTypes.GROUP,
  title: "General",
  canSave: true,
  settings: [
    BIZBRAINZ_INSTANCE_NAME_SETTING_SETTING,
    BIZBRAINZ__ADMIN_EMAILS_SETTING,
    BIZBRAINZ_DOWNLOAD_DOCKER_COMPOSE_FILE_SETTING,
    BIZBRAINZ_DISABLE_TELEMETRY_SETTING,
    BIZBRAINZ_HIDE_WATERMARK_SETTING,
  ],
} as AdminConfigType;
