import React from 'react';
import { browserHistory, Router } from 'react-router';
import { Provider, connect } from 'react-redux';
import PropTypes from 'prop-types';
import { ToastContainer, toast } from 'react-toastify';
import Login from '../routes/Login';
import 'react-toastify/dist/ReactToastify.min.css'

class App extends React.Component {
  static propTypes = {
    store: PropTypes.object.isRequired,
    routes: PropTypes.object.isRequired,
  };

  isLoggedIn () {
    return !!localStorage.getItem('accessToken') && !!localStorage.getItem('refreshToken');
  }

  render () {
    const { routes, store } = this.props;
    const page = this.isLoggedIn() ? <Router history={browserHistory} children={routes} /> : <Login />;

    return (
      <Provider store={store}>
        <div style={{ height: '100%' }}>
          <ToastContainer
            position="bottom-right"
            type="default"
            autoClose={5000}
            hideProgressBar={true}
            newestOnTop={false}
            closeOnClick
            pauseOnHover
          />
          {page}
        </div>
      </Provider>
    );
  }
}

const mapStateToProps = (state) => ({
  loginInfo: state.login
});

export default connect(mapStateToProps)(App);
