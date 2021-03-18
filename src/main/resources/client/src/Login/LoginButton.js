import React, { Component } from "react";
import img_elLoginBackground from "../static/images/TypewriterLoginButton2_elLoginBackground.png";

export default class LoginButton extends Component {
  // This component doesn't use any properties

  constructor(props) {
    super(props);

    this.state = {};
  }

  componentDidMount() {}

  componentWillUnmount() {}

  componentDidUpdate() {}

  render() {
    const style_elLoginBackground = {
      backgroundImage: "url(" + img_elLoginBackground + ")",
      backgroundSize: "100% 100%",
    };
    const style_elLOGIN = {
      fontSize: 36.7,
      color: "black",
      textAlign: "right",
    };

    return (
      <div className="TypewriterLoginButton2">
        <div className="foreground">
          <div className="elLoginBackground" style={style_elLoginBackground}>
            <div className="font-n1942report  elLOGIN" style={style_elLOGIN}>
              <div>{this.props.text}</div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}
