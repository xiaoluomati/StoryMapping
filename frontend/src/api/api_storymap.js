import * as API from './api'

export default {

  // getStoryMapRoles: storyid => {
  //   return API.GET(`storyroles/${storyid}`)
  // },

  getStoryMapList: userid => {
    return API.GET(`storymaplist/`, { 'userid': userid })
  },

  deleteStoryMap: storyid => {
    return API.DELETE(`storymaps/${storyid}`)
  },

  updateStoryMap: (storyid, params) => {
    return API.PUT(`storymaps/${storyid}`, params)
  }
}
