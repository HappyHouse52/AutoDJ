import React from 'react';
import { connect } from 'react-redux';
import qs from 'qs';
import { logIn, logInCallback } from './modules/login';

class Login extends React.Component {

  componentWillMount() {
    const urlParams = qs.parse(window.location.search.substr(1));

    const accessToken = urlParams['code'];
    const state = urlParams['state'];

    if (accessToken && state) {
      this.props.logInCallback(accessToken, state);
    }
  }

  render() {
    return (
      <div style={{ margin: '0 auto' }} >
        <button className='btn btn-primary' onClick={this.props.logIn}>
          Log In
        </button>
      </div>
    );
  }
}

const mapDispatchToProps = {
  logIn,
  logInCallback
};

const mapStateToProps = (state) => ({});

export default connect(mapStateToProps, mapDispatchToProps)(Login);
