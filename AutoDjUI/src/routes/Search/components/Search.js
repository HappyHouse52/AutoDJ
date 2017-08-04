import React from 'react';
import { Grid } from 'semantic-ui-react';
import _ from 'lodash';
import Select from 'react-select';
import { toast } from 'react-toastify';
import './Search.scss';

class TrackSearch extends React.Component {

  constructor() {
    super();
    this.state = {
      isLoading: false
    };

    _.bindAll(this,
      "handleSearchChange",
      "onChange"
    );

    this.handleSearchChange = _.debounce(this.handleSearchChange, 250);
  }

  componentWillUnmount() {
    this.setState({
      isLoading: false
    });
  }

  onChange(track) {
    this.setState({
      value: track
    });

    this.props.addTrackToQueue(track.value)
      .then(() => this.handleToast(track.value));
  }

  handleToast(track) {
    toast.info(`"${track.name} - ${track.artists[0].name}" added to the queue`);
  }

  handleSearchChange(value) {
    if (value === '') {
      return;
    }

    this.setState({
      isLoading: true
    });
    this.props.search(value)
      .then(() => this.setState({ isLoading: false }));
  }

  getOptions() {
    if (this.props.searchResults && this.props.searchResults.length === 0) {
      return;
    }

    return this.props.searchResults.map((result) => {
      return {
        value: result,
        label: `${result.name} - ${result.artists[0].name}`
      }
    });
  }

  render() {
    const { isLoading, value } = this.state;

    return (
      <Grid>
        <Grid.Column>
          <Select
            className='search-box'
            name='track-search'
            multi={false}
            placeholder='Search tracks...'
            isLoading={isLoading}
            value={value}
            options={this.getOptions()}
            onInputChange={this.handleSearchChange}
            onChange={this.onChange}
          />
        </Grid.Column>
      </Grid>
    );
  }
}

export default TrackSearch;