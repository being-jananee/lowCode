import { getBizbrainzConfigs } from "@bizBrainz/configs";

const { cloudServicesBaseUrl: BASE_URL } = getBizbrainzConfigs();

export const authorizeDatasourceWithBizbrainzToken = (bizBrainzToken: string) =>
  `${BASE_URL}/api/v1/integrations/oauth/authorize?bizBrainzToken=${bizBrainzToken}`;
