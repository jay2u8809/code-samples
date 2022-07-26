import childProcess from 'child_process';
import { CommandUtils, CurlOptions } from './command.utils';

const TAG = 'CommandUtilsSpec';

describe(TAG, () => {
  // beforeEach(async () => {});

  it.skip('execSync-curl', async () => {
    jest.setTimeout(999999999);

    const url = 'https://jsonplaceholder.typicode.com/posts';
    const options: CurlOptions = {
      headers: {
        'content-type': 'application/json; charset=UTF-8',
      },
      method: 'post',
      body: {
        title: 'foo',
        body: 'bar',
        userId: 1,
      },
    };
    const result = await CommandUtils.curlByCommand(url, options);
    console.log(TAG, 'result', result);

    expect(true);
  });

  it.skip('example execSync - curl', async () => {
    jest.setTimeout(999999999);

    /**
     * -X, --request: request method(GET, POST, PUT...)
     * -H, --header: header info
     * -F, : file
     * -d, --data: body data
     * -i, --include: response body + response header
     * -I, --head: response header
     * -v, --verbose: debug info
     * -w, --write-out: replace last char, (ex. -w '\n': % -> \n)
     */
    let cmd = '';
    let response = '';

    // GET one
    cmd = `curl -X GET https://jsonplaceholder.typicode.com/posts/1`;
    response = childProcess.execSync(cmd, {
      encoding: 'utf8',
    });
    console.log(TAG, 'get-one-curl-output', response);
    expect(response).toBeDefined();

    // GET list
    // cmd = `curl -X GET https://jsonplaceholder.typicode.com/posts`;
    // response = childProcess.execSync(cmd, {
    //   encoding: 'utf8',
    // });
    // console.log(TAG, 'get-list-curl-output', response);
    // expect(response).toBeDefined();

    // GET list - filtering resources (query string)
    // cmd = `curl -X GET https://jsonplaceholder.typicode.com/posts?userId=1`;
    // response = childProcess.execSync(cmd, {
    //   encoding: 'utf8',
    // });
    // console.log(TAG, 'get-list-querystring-curl-output', response);
    // expect(response).toBeDefined();

    // GET list - nested resources
    // cmd = `curl -X GET https://jsonplaceholder.typicode.com/posts/1/comments`;
    // response = childProcess.execSync(cmd, {
    //   encoding: 'utf8',
    // });
    // console.log(TAG, 'get-list-nested-curl-output', response);
    // expect(response).toBeDefined();

    // POST
    // cmd = `curl -X POST https://jsonplaceholder.typicode.com/posts -H 'content-type: application/json; charset=UTF-8' -d '{"title": "foo", "body": "bar", "userId": 1}'`;
    // response = childProcess.execSync(cmd, {
    //   encoding: 'utf8',
    // });
    // console.log(TAG, 'post-curl-output', response);
    // expect(response).toBeDefined();

    // PUT
    // cmd = `curl -X PUT https://jsonplaceholder.typicode.com/posts/1 -H 'content-type: application/json; charset=UTF-8' -d '{"id": 1, "title": "foo", "body": "bar", "userId": 1}'`;
    // response = childProcess.execSync(cmd, {
    //   encoding: 'utf8',
    // });
    // console.log(TAG, 'put-curl-output', response);
    // expect(response).toBeDefined();

    // PATCH
    // cmd = `curl -X PATCH https://jsonplaceholder.typicode.com/posts/1 -H 'content-type: application/json; charset=UTF-8' -d '{"title": "foo"}'`;
    // response = childProcess.execSync(cmd, {
    //   encoding: 'utf8',
    // });
    // console.log(TAG, 'patch-curl-output', response);
    // expect(response).toBeDefined();

    // DELETE
    // cmd = `curl -X DELETE https://jsonplaceholder.typicode.com/posts/1`;
    // response = childProcess.execSync(cmd, {
    //   encoding: 'utf8',
    // });
    // console.log(TAG, 'delete-curl-output', response);
    // expect(response).toBeDefined();
  });

  it.skip('spawn - curl', async () => {
    jest.setTimeout(999999999);

    let cmd = '';
    let response = undefined;
    cmd = `curl -X GET https://jsonplaceholder.typicode.com/posts/1`;
    response = childProcess.spawnSync(cmd, {
      encoding: 'utf8',
    });
    console.log(TAG, 'get-one-curl-output', response);
    console.log(
      TAG,
      'std',
      JSON.stringify(response.stdout),
      JSON.stringify(response.stderr),
      JSON.stringify(response.stdin),
    );
    expect(response).toBeDefined();

    expect(true);
  });
});
