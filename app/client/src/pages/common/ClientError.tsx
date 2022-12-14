import React from "react";
import { connect } from "react-redux";
import styled from "styled-components";
import { Button, Size } from "design-system";
import { flushErrors } from "actions/errorActions";
import PageUnavailableImage from "assets/images/404-image.png";

const Wrapper = styled.div`
  text-align: center;
  margin-top: 5%;
  .bold-text {
    font-weight: ${(props) => props.theme.fontWeights[3]};
    font-size: 24px;
  }
  .page-unavailable-img {
    width: 35%;
  }
  .button-position {
    margin: auto;
  }
`;

interface Props {
  flushErrors?: any;
}

function ClientError(props: Props) {
  const { flushErrors } = props;

  return (
    <Wrapper className="space-y-6">
      <img
        alt="Page Unavailable"
        className="page-unavailable-img"
        src={PageUnavailableImage}
      />
      <div className="space-y-2">
        <p className="bold-text">Whoops something went wrong!</p>
        <p>This is embarrassing, please contact Bizbrainz support for help</p>
        <Button
          category="primary"
          className="button-position"
          fill="true"
          icon="right-arrow"
          iconAlignment="right"
          onClick={() => {
            flushErrors();
            window.open("https://discord.gg/rBTTVJp", "_blank");
          }}
          size={Size.large}
          text="Contact us on discord"
        />
      </div>
    </Wrapper>
  );
}

export default connect(null, {
  flushErrors,
})(ClientError);
