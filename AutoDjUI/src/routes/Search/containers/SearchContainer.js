import { connect } from 'react-redux';
import { search } from '../modules/search';
import { addTrackToQueue } from '../../../store/queue';

import Search from '../components/Search';

const mapDispatchToProps = {
  search,
  addTrackToQueue
};

const mapStateToProps = (state) => ({
  searchResults : state.searchResults.items || []
});

export default connect(mapStateToProps, mapDispatchToProps)(Search);
