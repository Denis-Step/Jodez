import React, { Component } from "react";
import img_elSignupBackground from "../static/images/TypewriterSignupButton2_elSignupBackground.png";

export default class SignupButton extends Component {
  // This component doesn't use any properties

  constructor(props) {
    super(props);

    this.state = {};
  }

  componentDidMount() {}

  componentWillUnmount() {}

  componentDidUpdate() {}

  render() {
    const style_elSignupBackground = {
      backgroundImage: "url(" + img_elSignupBackground + ")",
      backgroundSize: "100% 100%",
    };
    const style_elSIGNUP = {
      fontSize: 33.0,
      color: "black",
      textAlign: "center",
    };

    return (
      <div className="TypewriterSignupButton2">
        <div className="foreground">
          <div className="elSignupBackground" style={style_elSignupBackground}>
            <div className="font-n1942report  elSIGNUP" style={style_elSIGNUP}>
              <div>{this.props.text}</div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}
