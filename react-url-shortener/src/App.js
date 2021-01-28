import React, { Component } from "react";
import "./bootstrap.min.css";
import "./App.css";
import ShortenerComponent from "./components/ShortenerComponent"


class App extends Component {

  render() {
    return (
      <ShortenerComponent></ShortenerComponent>
    );
  }
}

export default App;