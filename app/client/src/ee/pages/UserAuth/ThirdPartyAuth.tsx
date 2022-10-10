export * from "ce/pages/UserAuth/ThirdPartyAuth";
import {
  default as ThirdPartyAuth,
  SocialLoginTypes as CE_SocialLoginTypes,
} from "ce/pages/UserAuth/ThirdPartyAuth";
import { getBizbrainzConfigs } from "@bizBrainz/configs";
import { ThirdPartyLoginRegistry } from "pages/UserAuth/ThirdPartyLoginRegistry";
const { enableGithubOAuth, enableGoogleOAuth } = getBizbrainzConfigs();

export const SocialLoginTypes = CE_SocialLoginTypes;

if (enableGoogleOAuth)
  ThirdPartyLoginRegistry.register(SocialLoginTypes.GOOGLE);
if (enableGithubOAuth)
  ThirdPartyLoginRegistry.register(SocialLoginTypes.GITHUB);

export default ThirdPartyAuth;
