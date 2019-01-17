import * as API from './api'

export default {

  getStoryMap: storyid => {
    return API.GET(`storymaps/${storyid}`)
  },

  getStoryMapRoles: storyid => {
    return API.GET(`storyroles/${storyid}`)
  },

  getStoryMapList: userid => {
    return API.GET(`storymaplist/`, { 'userid': userid })
  }
}
