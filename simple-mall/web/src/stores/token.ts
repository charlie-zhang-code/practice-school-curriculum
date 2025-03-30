// TODO
interface TokenState {
  token: string;
}

// Token 持久化
export const useTokenStore = defineStore("token-store", {
  state: (): TokenState => {
    return {
      token: "",
    };
  },
});
