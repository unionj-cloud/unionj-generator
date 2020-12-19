import { shallowMount } from '@vue/test-utils';
import Usage from '@/components/Usage.vue';

describe('HelloWorld.vue', () => {
  it('renders props.msg when passed', () => {
    const msg = 'new message';
    const wrapper = shallowMount(Usage, {
      propsData: { msg },
    });
    expect(wrapper.text()).toMatch(msg);
  });
});
