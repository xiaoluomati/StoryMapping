import * as API from './api'

export default {

  getStoryMap: storyid => {
    return API.GET(`storymaps/${storyid}`)
  }

}
