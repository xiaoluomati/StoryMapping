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
  },

  deleteStoryMap: storyid => {
    return API.DELETE(`storymaps/${storyid}`)
  },

  // 格式如下：
  // {
  //   "id": "2",
  //   "title": "storymap2",
  //   "description": "this is a test description for storymap2"
  // }
  updateStoryMap: (storyid, params) => {
    return API.PUT(`storymaps/${storyid}`, params)
  }
}
