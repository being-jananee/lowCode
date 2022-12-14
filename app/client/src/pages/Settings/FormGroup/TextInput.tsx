import FormTextField from "components/utils/ReduxFormTextField";
import { createMessage } from "@bizBrainz/constants/messages";
import React from "react";
import { FormGroup, SettingComponentProps } from "./Common";

export default function TextInput({ setting }: SettingComponentProps) {
  return (
    <FormGroup
      className={`t--admin-settings-text-input t--admin-settings-${setting.name ||
        setting.id}`}
      setting={setting}
    >
      <FormTextField
        name={setting.name || setting.id || ""}
        placeholder={createMessage(() => setting.placeholder || "")}
        type={setting.controlSubType}
      />
    </FormGroup>
  );
}
