import {EntityRepository, Repository} from "typeorm";
import {Member} from "../../entities/member/member";

// TODO TEMP FILE

@EntityRepository()
export class MemberRepository extends Repository<Member> {

  findByName(firstName: string, lastName: string) {
    return this.createQueryBuilder("user")
      .where("user.firstName = :firstName", { firstName })
      .andWhere("user.lastName = :lastName", { lastName })
      .getMany();
  }

}
